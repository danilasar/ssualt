# Ssualt man website

## How to debug

Run with hot reload:

```sh
./sass -w src/resources/static/style # don't forget run sass deamon
./gradlew build --continuous # for hot reload
./gradlew buildRun # for server application
```

## How to use

Mansite has SPA, static and text interface. You can load localhost:8080 in
your browser and use a reactive search form. If your browser doesn't support
javascript, you'll see a submit button.

Also if you not need (or can't visualise) a html ui, you can work in a text
mode: if your browser doesn't send text/html in Accept header, the server will
return text interface. Endpoints:

```sh
curl localhost:8080 # index page
curl localhost:8080?q=QUESTION # search page with your QUESTION
curl localhost:8080/a/ID # man page №ID
```
