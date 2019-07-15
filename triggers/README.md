# Exercise 3 - Using Cloud Function Triggers

Triggers for events are an integral part of building Serverless applications. In this exercise, we'll create triggers to invoke our Cloud Functions, rather than just calling them manually from the CLI or Web browser.

Goals:
* Setup triggers to invoke Cloud Functions 
* Learn how to:
    * Create a trigger 
    * Use an alarm trigger to call a Cloud Function every N minutes

## Understanding Triggers

Events are a key part of Serverless Architecture. Your instructor will discuss them in detail before this exercise.

Triggers are things that fire events, which invoke your Cloud Functions. You can create triggers that generate events for many different types of things:
    * Schedule alarms (like a crontab)
    * Message bus (like Message Hub)
    * Database changes
    * Github commits
    * ... or anything else with custom triggers!

## Create a basic trigger

* Create a trigger for our helloJava Cloud Function

```ibmcloud fn trigger create helloJavaTrigger```

* See if it got created:

```ibmcloud fn trigger list```

* Fire it

```ibmcloud fn trigger fire helloJavaTrigger```

## Creating a rule

We need to create a rule to associate a trigger with a Cloud Function. 
* Let's create one to invoke our helloJava function:

```ibmcloud fn rule create helloJavaRule helloJavaTrigger helloJava```

* Test the new rule:

```ibmcloud fn trigger fire helloJavaTrigger --param name PratikRules```

* See that it was fired

```ibmcloud fn activation get --last```
 ibmcloud fn trigger fire locationUpdate
 
## Create an alarm trigger

Let's create a trigger that fires once and invokes one of our existing Cloud Functions.

* In a separate terminal window, first run to monitor Cloud Function invokations:

```ibmcloud fn activation poll ```

* Create a trigger to invoke our helloJava Cloud function (adjust the time, note the time is in UTC:

```
ibmcloud fn trigger create fireOnceTrigger \
  --feed /whisk.system/alarms/once \
  --param date "2019-02-03T16:32:00.000Z" \
  --param trigger_payload "{\"name\":\"FireOnceTrigger\"}" \
  --param deleteAfterFire "rules" 
```

* Associate this trigger with a new rule:

```ibmcloud fn rule create helloJavaOnceRule fireOnceTrigger helloJava```

In a later exercise, we'll create a trigger to fire when we change a record in our database

## Exercise review

Our objectives in this exercise:

* Setup triggers to invoke Cloud Functions 
* Learn how to:
    * Create a trigger    
    * Use an alarm trigger to call a Cloud Function every N minutes

***Please ask your friendly workshop instructor if you have any questions!***

## EXTRA CREDIT
So you've finished before everyone else? No problem, here's some more stuff you can dive into!

* Create a recurring trigger:

```ibmcloud fn trigger create interval --feed /whisk.system/alarms/interval --param minutes "<minutes>" --param trigger_payload "{<key>:<value>,<key>:<value>}" --param startDate "<start_date>" --param stopDate "<stop_date>"```
    
   
   
