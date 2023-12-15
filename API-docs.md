# users table.

`GET`: /users/{userName (String)}

`POST`: /users -- Body (JSON): {
        "userName": String -> PK,
        "firstName": String,
        "lastName": String,
        "password": String,
        "address": String,
        "email": String -> UNIQUE,
        "dob": "YYYY-MM-DD"
}

`PUT`: /users -- Body (JSON): {
        "firstName": String,
        "lastName": String,
        "password": String,
        "address": String,
        "email": String -> UNIQUE,
        "dob": "YYYY-MM-DD"
}

`DELETE`: /users/{userName (String)}


# posts table.

`GET`: /posts

`GET`: /posts/{authorId (String)}

`POST`: /posts -- Body (JSON): {
        "authorId": String,
        "textualContent": String
}

`PUT`: /posts -- Body (JSON):  {
        "postId": Long,
        "textualContent": String
}

`DELETE`: /posts/{postId (Long)}


# profile_pictures table.

`GET`: /profile_pictures/user/{authorId (String)} -> returns all profile pictures for user

`GET`: /profile_pictures/picture/{profilePictureId (Long)} -> returns specific profile picture

`POST`: /profile_pictures -- Body (JSON): {
        "link": String,
        "authorId": String
}

`DELETE`: /profile_pictures/user/{authorId (String)}
}

# comments table.

`GET`: /comments/{postId (Long)}

`POST`: /comments -- Body (JSON): {
        "postId": Long,
        "textualContent": String,
        "authorId": String
}

`PUT`: /comments -- Body (JSON): {
        "commentId": Long,
        "textualContent": String
}

`DELETE`: /comments/{commentId (Long)}


# relation_ships table.

`GET`: /relation_ships/{targetId (String)}/{type (RelationShips ENUM)}

`POST`: /relation_ships -- Body (JSON): {
        "sourceId": String,
        "targetId": String,
        "type": RelationShips ENUM
}

`DELETE`: /relation_ships/{sourceId (String)}/{targetId (String)}


# reactions table.

`GET`: /reactions/{postId (Long)}

`GET`: /reactions/{postId {Long}}/{type (Reactions ENUM)}

`POST`: /reactions -- Body (JSON): {
        "postId": Long,
        "authorId": String,
        "type": Reactions ENUM
}

`PUT`: /reactions -- Body (JSON): {
        "postId": Long,
        "authorId": String,
        "type": Reactions ENUM
}

`DELETE`: /reactions/{postId (Long)}/{authorId (String)}
