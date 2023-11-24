# Person Table.

`GET`: localhost:8080/users/{user_name}

`GET`: localhost:8080/users/{firstName} {lastName}

`POST`: localhost:8080/users -- Body (JSON): {
                                                "userName": String -> UNIQUE,
                                                "firstName": String,
                                                "lastName": String,
                                                "password": String,
                                                "address": String,
                                                "email": String -> UNIQUE,
                                                "dob": "YYYY-MM-DD",
                                                "activeProfilePicture": ProfilePicture
                                             }

`PUT`: localhost:8080/users/{user_name}?firstName= &
                                      lastName= &
                                      password= &
                                      address= &
                                      email= &
                                      dob= &
                                      activeProfilePicture= &

`DELETE`: localhost:8080/users/{user_name}


# Post Table.

`GET`: localhost:8080/posts

`GET`: localhost:8080/posts/{user_name}

`POST`: localhost:8080/posts -- Body (JSON): {
                                                "textualContent": String,
                                                "date": "YYYY-MM-DD",
                                                "author": Person
                                             }

`PUT`: localhost:8080/posts/{post_id}?textualContent=

`DELETE`: localhost:8080/posts/{post_id}


# Profile_Picture Table.

`GET`: localhost:8080/users/{user_name}/profile_pictures

`GET`: localhost:8080/users/{user_name}/profile_pictures/{profile_pictures_id}

`POST`: localhost:8080/users/{user_name}/profile_pictures -- Body (JSON): {
                                                "link": String,
                                                "author": Person
                                             }

`DELETE`: localhost:8080/users/{user_name}/profile_pictures/{profile_pictures_id}


# Comment Table.

`GET`: localhost:8080/comments/{post_id}

`POST`: localhost:8080/comments/{post_id} -- Body (JSON): {
                                                "textualContent": String,
                                                "date": "YYYY-MM-DD",
                                                "author": Person,
                                                "post": Post
                                             }

`PUT`: localhost:8080/comments/{post_id}/{comment_id}?textualContent=

`DELETE`: localhost:8080/comments/{post_id}/{comment_id}


# Realtion_Ship Table.

`GET`: localhost:8080/{target_id}/relation_ships/{type}

`POST`: localhost:8080/{target_id}/relation_ships -- Body (JSON): {
                                                "source": Person,
                                                "target": Person,
                                                "type": Short,
                                                "date": LocalDate
                                             }

`PUT`: localhost:8080/{target_id}/relation_ships/{source_id}?type=

`DELETE`: localhost:8080/{target_id}/relation_ships/{source_id}


# Reaction Table.

`GET`: localhost:8080/reactions/{post_id}

`GET`: localhost:8080/reactions/{post_id}/{type}

`POST`: localhost:8080/reactions/{post_id} -- Body (JSON): {
                                                "author": Person,
                                                "post": Person,
                                                "type": Short,
                                                "date": LocalDate
                                             }

`PUT`: localhost:8080/reactions/{post_id}/{user_name}?type=

`DELETE`: localhost:8080/reactions/{post_id}/{user_name}