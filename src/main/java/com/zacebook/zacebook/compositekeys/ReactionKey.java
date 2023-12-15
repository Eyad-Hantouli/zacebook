package com.zacebook.zacebook.compositekeys;

import com.zacebook.zacebook.tables.User;
import com.zacebook.zacebook.tables.Post;
import java.io.Serializable;

public class ReactionKey implements Serializable {
    private User author;
    private Post post;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
