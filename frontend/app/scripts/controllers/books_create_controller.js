Frontend.BooksCreateController = Ember.ObjectController.extend({
  showRemove: function() {
    return this.get('model.authors').length > 1;
  }.property("model.authors.@each.id"),
  
  actions: {
    addAuthor: function() {
      this.get('model.authors').pushObject(Ember.Object.create({id: new Date().getTime()}));
    },
    removeAuthor: function(id) {
      var authors = this.get('model.authors');
      authors.removeObjects(authors.filterBy("id", id));
    },
    save: function() {
      var newBook = this.get("model");
      var book = this.get("store").createRecord("book", {
        title: newBook.title,
        isbn: newBook.isbn
      });
      var controller = this;
      book.get("authors").then(function(authors) {
        console.log(newBook.authors);
        newBook.authors.forEach(function(author){
          authors.addObject(controller.get("store").createRecord("author", {
            lastName: author.get("lastName"),
            firstName: author.get("firstName")
          }));
        });
        book.save();
      });
    }
  }
});