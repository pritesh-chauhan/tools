'use strict';

const todoController = require('../controllers/todoController');

module.exports = (app) => {
    app.route('/todo')
        .get(todoController.list)
        .post(todoController.save);

     app.route('/todo/:id')
         .get(todoController.get)
         .put(todoController.update)
         .delete(todoController.delete);
};