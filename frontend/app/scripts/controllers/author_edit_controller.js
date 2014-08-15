Frontend.AuthorEditController = Ember.ObjectController.extend({
  actions: {
    delete: function() {
      var controller = this;
      var author = this.get('model');
      author.destroyRecord().then(function() {
        controller.get('store').unloadAll('book');
        controller.transitionToRoute('authors');
      },function(response) {
        //author.rollback(); - does not work
        controller.set("error", response.responseJSON.message);
      });
    },
    save: function() {
      var controller = this;
      var author = controller.get('model');
      author.save().then(function(response) {
        controller.transitionToRoute('author.index', response);
      },function(response) {
        controller.set("error", response.responseJSON.message);
      });
    },
    removeBook: function(id) {
      var controller = this;
      this.get('store').find('book', id).then(function(book){
        var author = controller.get('model');
        book.get('authors').removeObject(author);
        if (book.get('authors.length') === 0) {
          book.deleteRecord();
        }
        book.save().then(function() {
          author.get('books').removeObject(book);
          author.set('nbooks', author.get('nbooks')-1);
          controller.set("error", "");
        },function(response) {
          book.rollback();
          controller.set("error", response.responseJSON.message);
        });
      });
    },
    saveBook: function() {
      var controller = this;
      var book = this.get("store").createRecord("book", {
        title: controller.get("title"),
        isbn: controller.get("isbn")
      });
      var author = this.get('model');
      book.get("authors").then(function(authors) {
        authors.addObject(author);
        book.save().then(function(response) {
          author.get("books").addObject(response);
          author.set('nbooks', author.get('nbooks')+1);
          controller.set("title", "");
          controller.set("isbn", "");
          controller.set("error", "");
        },function(response) {
          book.deleteRecord();
          controller.set("error", response.responseJSON.message);
        });
      });
    }
  }
});