'use strict';

const todoRoute = require('./todoRoute');

module.exports = (app) => {
    todoRoute(app);
};