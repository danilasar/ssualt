# Ssualt man website

## How to debug

Run with hot reload:

```sh
./sass -w src/resources/static/style # don't forget run sass deamon
./gradlew build --continuous # for hot reload
./gradlew buildRun # for server application
```

## How to use

The man site has reactive, static and text interface. You can open localhost:8080 in
your browser and use the reactive search form. If your browser doesn't support
javascript, you'll see a submit button.

Also, if you don't need (or can't visualise) an html ui, you can work in text
mode: if your browser doesn't send text/html in Accept header, the server
returns the text interface. Endpoints:

```sh
curl localhost:8080 # index page
curl localhost:8080?q=QUESTION # search page with your QUESTION
curl localhost:8080/a/ID # man page №ID
```
