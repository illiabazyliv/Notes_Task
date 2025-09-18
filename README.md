##Documentation 
http://localhost:8080/swagger-ui/index.html#
##Database 
http://localhost:8080/h2-console
##Endpoints
Authors
GET http://localhost:8080/authors/{id}
GET http://localhost:8080/authors
POST http://localhost:8080/authors
BODY 
{
"name": 
}
NOTES
GET http://localhost:8080/notes
GET http://localhost:8080/notes/{id}
DELETE http://localhost:8080/notes/{id}
POST http://localhost:8080/notes
body
    {
"title": "",
"content": "",
"authorId": 1,
    }