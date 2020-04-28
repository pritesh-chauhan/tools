'use strict';

const todoService = require('./../services/todoService');

/**
 * Sets response for todo search.
 *
 * @param request
 * @param response
 */
exports.list = (request, response) => {
    const title = request.query.tile;
    const params = {};
    if(title) {
        params.title = title
    };
    const promise = todoService.search(params);
    const result = (todos) => {
        response.status(200);
        response.json(todos);
    };
    promise
        .then(result)
        .catch(renderErrorResponse(response));
};

/**
 * Creates a new todo and sets the response.
 *
 * @param request
 * @param response
 */
exports.save = (request, response) => {
    const todo = Object.assign({}, request.body);
    const result = (savedTodos) => {
        response.status(201);
        response.json(savedTodos);
    };
    const promise = todoService.save(todo);
    promise
        .then(result)
        .catch(renderErrorResponse(response));
};

/**
 * Returns todo response.
 *
 * @param request
 * @param response
 */
exports.get = (request, response) => {
    const todoId = request.params.id;
    const result = (todo) => {
        response.status(200);
        response.json(todo);
    };
    const promise = todoService.get(todoId);
    promise
        .then(result)
        .catch(renderErrorResponse(response));
};

/**
 * Updates the todo resource.
 *
 * @param request
 * @param response
 */
exports.update = (request, response) => {
    const todoId = request.params.id;
    const updatedTodo = Object.assign({}, request.body);
    var curr_date = new Date();
    updatedTodo.updatedAt = curr_date;
    updatedTodo.id = todoId;
    const result = (todo) => {
        response.status(200);
        response.json(todo);
    };
    const promise = todoService.update(updatedTodo);
    console.log(promise);
    promise
        .then(result)
        .catch(renderErrorResponse(response));
};

/**
 * Deletes an todo resource.
 *
 * @param request
 * @param response
 */
exports.delete = (request, response) => {
    const todoId = request.params.id;
    const result = () => {
        response.status(200);
        response.json({
            message: "Successfully Deleted the item."
        });
    };
    const promise = todoService.delete(todoId);
    promise
        .then(result)
        .catch(renderErrorResponse(response));
};


/**
 * Throws error if error object is present.
 *
 * @param {Response} response The response object
 * @return {Function} The error handler function.
 */
let renderErrorResponse = (response) => {
    const errorCallback = (error) => {
        if (error) {
            response.status(500);
            response.json({
                message: error.message
            });
        }
    };
    return errorCallback;
};