import mongoose, { Schema } from 'mongoose'

const S = require('string');
const restaurantSchema = new Schema({
  name: {
    type: String
  },
  address: {
    type: String
  },
  intolerance: [{
    type: Schema.ObjectId,
    ref: 'Intolerance'
  }],
  timetable: {
    type: String
  },
  description:{
    type:String

  },
  picture: {
    type:String

  },
  loc: {
    type: [Number],
    get: (v) => (v && v.length > 0) ? v.join() : null,
    set: (v) => (S(v).isEmpty()) ? null : v.split(',').map(Number)
  }
}, {
  strict: false,
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

restaurantSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      name: this.name,
      address: this.address,
      intolerance: this.intolerance,
      timetable: this.timetable,
      description: this.description,
      picture: this.picture,
      loc: this.loc,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

/*restaurantSchema.pre('remove', {query:true}, function(next){
  Photo    
    .find({restaurantId: this.id})
    .exec((err, result) => {
      Promise.all(result.map(photorestaurant => photorestaurant.remove()))
      next()
    }
    )
})*/

restaurantSchema.index({loc: '2dsphere'})

const model = mongoose.model('Restaurant', restaurantSchema)

export const schema = model.schema
export default model
