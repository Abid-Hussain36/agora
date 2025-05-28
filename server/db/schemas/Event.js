import mongoose from 'mongoose';
import {INTERESTS} from "../../../shared/enums.js";
import {EVENT_TYPES} from "../../../shared/enums.js";

const eventSchema = new mongoose.Schema({
    name: {type: String, required: true},
    description: {type: String},
    startTime: {type: Date, required: true},
    endTime: {type: Date},
    universityAffiliation: {type: String, required: true},
    registrationLimit: {type: Number},
    open: {type: Boolean, required: true},
    subject: {type: String},
    streetAddress: {type: String},
    city: {type: String},
    state: {type: String},
    country: {type: String},
    zipCode: {type: String},
    type: {type: String, enum: EVENT_TYPES, required: true},
    interests: {type: [{type: String, enum: INTERESTS}], default: []},
    host: {type: mongoose.Schema.Types.ObjectId, ref: "User", required: true},
    attendees: {type: [{type: mongoose.Schema.Types.ObjectId, ref: "User"}], default: []},
});

export default mongoose.model("Event", eventSchema);