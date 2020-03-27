'use strict';
const mongoose = require('mongoose');
const Schema = mongoose.Schema;

/**
 * Mongoose schema for todo object.
 */
let TodoSchema = new Schema({
        title: {
            type: String,
            required: "Title needs to be passed"
        },
        description: {
            type: String,
            required: "Description needs to be passed"
        },
        createdAt: {
            type: Date,
            default: Date.now
        },
        updatedAt: {
            type: Date,
            default: Date.now
        },
		check: {
			type: Boolean,
			default: false
		}
    },
    {
        versionKey: false
    });
// Duplicate the id field as mongoose returns _id field instead of id.
TodoSchema.virtual('id').get(function(){
    return this._id.toHexString();
});

// Ensure virtual fields are serialised.
TodoSchema.set('toJSON', {
    virtuals: true
});

module.exports = mongoose.model('todo', TodoSchema);