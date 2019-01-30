package com.ibm.cloud.functions;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import com.cloudant.client.org.lightcouch.NoDocumentException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.cloudant.client.api.model.Document;

import static com.cloudant.client.api.query.Expression.eq;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

public abstract class CloudantObjectStore<T extends BaseCloudantObject> {

    private Class<T> clazz;

    private Database db = null;

    // String apikey, String host
    public CloudantObjectStore(JsonObject args, String dbName) {
        CloudantClient client = ServiceHelper.createClient(args);
        if (client != null) {
            db = client.database(dbName, true);
        }
        // a little magic to save some boilerplate in the constructor
        // also, did I mention how much I hate generics sometimes?
        this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /*
        Returns all of the entries only of the "type" that is represented by this
        Store / Collection
     */
    public Collection<T> findAll() {
        QueryResult<T> qr;
        QueryBuilder qb = new QueryBuilder(
                eq("type", clazz.getName()));

        System.out.println("Generated query: " + qb.build());

        qr = db.query(qb.build(), clazz);

        return qr.getDocs();
    }


    /*
        Returns ALL documents in the database, regardless of our surrogate "type" field
     */
    public List<Document> getAllDocs() {
        List docs;
        try {
            docs = db.getAllDocsRequestBuilder().build().getResponse().getDocs();
        } catch (IOException e) {
            return null;
        }
        return docs;
    }


    public T findById(String id) {
        try {
            return db.find(clazz, id);
        } catch (NoDocumentException e) {
            return null;
        }

    }


    public T persist(T td) {
        String id = db.save(td).getId();
        return db.find(clazz, id);
    }


    /*
      Updates a Cloudant stored object with a new one.
      You must manually copy the original object's fields over (except _rev and _id) to the new object.

     */
    public T update(String id, T newT) {
        T t = db.find(clazz, id);
        // For cloudant, you must supply the _id and _rev
        newT.set_id(t.get_id());
        newT.set_rev(t.get_rev());

        Response response = db.update(newT);
        // We can either set the new rev, or just get the entire updated obj
        //        newT.set_rev(response.getRev());

        return db.find(clazz, id);
    }


    public void delete(T t) {
        db.remove(t);
    }


    public long count() {
        return findAll().size();
    }

    public T cloneObject(T o){
        Gson gson = new Gson();
        String serializedObject = gson.toJson(o);
        T object = (T)gson.fromJson(serializedObject, o.getClass());
        return object;
    }


}
