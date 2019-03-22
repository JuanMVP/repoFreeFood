import { success, notFound } from '../../services/response/'
import { Restaurant } from '.'
import { Intolerance} from '../intolerance'
import { User } from '../user'


/*export const create = ({ bodymen: { body } }, res, next) =>
  Restaurant.create(body)
    .then((restaurant) => restaurant.view(true))
    .then(success(res, 201))
    .catch(next)*/

    const uploadService = require('../../services/upload/')


export const create = (req, res, next) => {
  let restaurantCreado;

  uploadService.uploadFromBinary(req.file.buffer)
    .then((json) =>
      Restaurant.create({
        name: req.body.name,
        address: req.body.address,
        intolerance: req.body.intolerance,
        description: req.body.description,
        timetable: req.body.timetable,
        picture: json.data.link,
        loc: req.body.loc
      })
    )
    .then((restaurant) => {
      restaurantCreado = restaurant;
      return Intolerance.findByIdAndUpdate(restaurant.intolerance, { $push: { restaurants: restaurant } }).exec()

    })
    .then((intolerance) => restaurantCreado.view(true))
    .then(success(res, 201))
    .catch(err => {
      console.log(err)
      next(err)
    })
}



export const index = ({ querymen: { query, select, cursor } }, res, next) =>
  Restaurant.count(query)
    .then(count => Restaurant.find(query, select, cursor)
      .populate('intolerance', 'name')
      .exec()
      .then((restaurants) => ({
        count,
        rows: restaurants.map((restaurant) => restaurant.view())
      }))
    )
    .then(success(res))
    .catch(next)

export const show = ({ params }, res, next) =>
  Restaurant.findById(params.id)
    .populate('intolerance', 'name')
    .exec()
    .then(notFound(res))
    .then((restaurant) => restaurant ? restaurant.view() : null)
    .then(success(res))
    .catch(next)

export const update = ({ bodymen: { body }, params }, res, next) =>
  Restaurant.findById(params.id)
    .then(notFound(res))
    .then((restaurant) => restaurant ? Object.assign(restaurant, body).save() : null)
    .then((restaurant) => restaurant ? restaurant.view(true) : null)
    .then(success(res))
    .catch(next)

export const destroy = ({ params }, res, next) =>
  Restaurant.findById(params.id)
    .then(notFound(res))
    .then((restaurant) => restaurant ? restaurant.remove() : null)
    .then(success(res, 204))
    .catch(next)


export const addFavorite = ({ user, params }, res, next) =>
  User.findByIdAndUpdate(user.id, { $addToSet: { restaurantsfavs: params.id } }, { new: true })
    .then(success(res, 200))
    .catch(next)

export const delFavorite = ({ user, params }, res, next) =>
  User.findByIdAndUpdate(user.id, { $pull: { restaurantsfavs: params.id } }, { new: true })
    .then(success(res, 200))
    .catch(next)

    .populate('intolerance', 'name')

export const userFavorites = ({ user, querymen: { query, select, cursor } }, res, next) => {
  query['_id'] = { $in: user.restaurantsfavs }
  Restaurant
    .find(query, select, cursor)
    .exec()
    .then(success(res,200))
    .catch(next)
}
