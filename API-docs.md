# Person Table.

`GET`: /users/{userName (String)}

`POST`: /users -- Body (JSON): {
        "userName": String -> PK,
        "firstName": String,
        "lastName": String,
        "password": String,
        "address": String,
        "email": String -> UNIQUE,
        "dob": "YYYY-MM-DD",
        "activeProfilePicture": ProfilePictureId
}
`PUT`: /users -- Body (JSON): {
       "userName": String -> PK,
        "firstName": String,
        "lastName": String,
        "password": String,
        "address": String,
        "email": String -> UNIQUE,
        "dob": "YYYY-MM-DD",
        "activeProfilePicture": ProfilePictureId
}

`DELETE`: /users/{userName (String)}


# Post Table.

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


# Profile_Picture Table.

`GET`: /profile_pictures/user/{authorId (String)} -> returns all profile pictures for user

`GET`: /profile_pictures/picture/{profilePictureId (Long)} -> returns specific profile picture

`POST`: /profile_pictures -- Body (JSON): {
        "link": String,
        "authorId": String
}

# Comment Table.

`GET`: /comments/{postId (Long)}

`POST`: /comments -- Body (JSON): {
        "commentId": Long,
        "postId": Long,
        "textualContent": String,
        "authorId": String
}

`PUT`: /comments -- Body (JSON): {
        "commentId": Long,
        "postId": Long,
        "textualContent": String,
        "authorId": String
}

`DELETE`: /comments/{postId (Long)}/{commentId (Long)}


# Realtion_Ship Table.

`GET`: /relation_ships/{targetId (String)}/{type (RelationShips ENUM)}

`POST`: /relation_ships -- Body (JSON): {
        "sourceId": String,
        "targetId": String,
        "type": RelationShips ENUM
}

`DELETE`: /relation_ships/{targetId (String)}/{sourceId (String)}


# Reaction Table.

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