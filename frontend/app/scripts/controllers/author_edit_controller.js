Frontend.AuthorEditController = Ember.ObjectController.extend({
  actions: {
    delete: function() {
      this.get('model').destroyRecord();
      this.transitionToRoute('authors');
    },
    removeBook: function(id) {
      var controller = this;
      this.get('store').find('book', id).then(function(book){
        var author = controller.get('model');
        author.get('books').removeObject(book);
        author.set('nbooks', author.get('nbooks')-1);
        book.get('authors').removeObject(author);
        if (book.get('authors.length') === 0) {
          book.deleteRecord();
        }
        book.save().then(function() {
        },function(response) {
          console.error(response);
        });
      });
    },
    save: function() {
      var controller = this;
      var author = controller.get('model');
      author.save().then(function(response) {
        controller.transitionToRoute('author.index', response);
      },function(response) {
        console.error(response);
      });
    }
  }
});