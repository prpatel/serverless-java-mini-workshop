# Build and Deploy a Cloud Function from the Command Line

Now that you've setup the command line utilities and gotten a taste of running Cloud Functions from the Web Console, it's time to write some code and deploy it!

Goals:
* Get used to using the CLI to build, deploy and debug development of Cloud Functions, and the structure of Cloud Function code. 
* Learn how to:
    * Examine the source for a  Cloud Function and write one 
    * Build it
    * Deploy it
    * Look at logs messages

## Build and deploy your first Serverless Java App!

Go to the exercise1 folder, then:

1. `mvn package`  

2. `ibmcloud fn action create helloJava target/hello-world-java.jar --main com.example.FunctionApp`

3. `ibmcloud fn action invoke --result helloJava --param name <YOUR_NAME>` 

(please use YOUR_NAME)

You should see:

```
{
    "greetings": "Hello <YOUR_NAME>"
}
```

"`--result`" means just show the results. Omit that, and see what you get back :)
This also adds the "`--blocking`" flag, discussed below.

#### Open FunctionApp.java in this project and examine it

* We're using the GSON library to work with JSON in Java
* Cloud Functions need a single "main" method with JSON in and JSON out:

``` public static JsonObject main(JsonObject args) ```

* We use the GSON library to parse the JSON args (params) and to create a response in JSON  

### 6. Create a HTML Web Cloud Function

So far we've just been creating JSON return values. What if we want to return some HTML?

* Create a new class, called WebHello.java (it's OK to copy FunctionApp.java)

* Change the code so that the response has a body with some HTML, like so:

``` response.addProperty("body", "<html><body><h3>" + result + "</h3></body></html>");  ```

* Create a web-enabled Cloud Function using this command:

``` ibmcloud fn action create webHello target/hello-world-java.jar --main com.example.WebHello --web true ``` 

* Use the command from the previous section to get its URL

``` 
ibmcloud fn action get webHello --url
```
* Invoke it by pasting the URL into the browser. If you appended .json to it, you'll get the it in json format. Do not append .json to it - and you'll see an a message wrapped in an H3 tag!
* What about the name parameter this Cloud Function takes? Append this to the URL:
``` 
webHello?name=Pratik
```
Of course, put your name instead of mine :)  

## Exercise review

Our objectives in this exercise:

* Get used to using the CLI to build, deploy and debug development of Cloud Functions, and the structure of Cloud Function code. 
* Learn how to:
    * Examine the source for a Cloud Function and write one 
    * Build it
    * Deploy it
    * Look at logs messages

***Please ask your friendly workshop instructor if you have any questions!***

## EXTRA CREDIT
So you've finished before everyone else? No problem, here's some more stuff you can dive into!

* Create a more sophisticated Cloud Function called Recipe:
    * Take an parameter called "dish"
    * Take one of these three as valid input for types of dishes: pizza, pasta, chips
    * Return a properly formatted JSON object with the ingredients for each dish. Example output for pizza: 
    ``` 
    {"ingredients" : ["dough", "cheese", "tomato sauce"]
    ```

### Extra Extra Credit
 * Create a HTML Cloud Function that sends an HTML page where you can pick from our foods: dishes: pizza, pasta, chips
 * User selects one of these, and sends to the above Cloud Fn with the param of "dish" 
 * Convert the Cloud Function to return HTML and show the list of ingredients properly formatted
    
   
   
