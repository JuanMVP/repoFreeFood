import { success, notFound } from '../../services/response/'
import { Photorestaurant } from '.'

const uploadService = require('../../services/upload/')
export const create = (req, res, next) => {
// export const create = ({ bodymen: { body } }, res, next) => {
  uploadService.uploadFromBinary(req.file.buffer)
    .then(json => Photo.create({
      restaurantId: req.body.restaurantId,
      imgurLink: json.data.link,
      deletehash: json.data.deletehash
    }))
    .then((photo) => photo.view(true))
    .then(success(res, 201))
    .catch(next)
}

export const index = ({ querymen: { query, select, cursor } }, res, next) =>
  Photorestaurant.count(query)
    .then(count => Photorestaurant.find(query, select, cursor)
      .then((photorestaurants) => ({
        count,
        rows: photorestaurants.map((photorestaurant) => photorestaurant.view())
      }))
    )
    .then(success(res))
    .catch(next)

export const show = ({ params }, res, next) =>
  Photorestaurant.findById(params.id)
    .then(notFound(res))
    .then((photorestaurant) => photorestaurant ? photorestaurant.view() : null)
    .then(success(res))
    .catch(next)

export const update = ({ bodymen: { body }, params }, res, next) =>
  Photorestaurant.findById(params.id)
    .then(notFound(res))
    .then((photorestaurant) => photorestaurant ? Object.assign(photorestaurant, body).save() : null)
    .then((photorestaurant) => photorestaurant ? photorestaurant.view(true) : null)
    .then(success(res))
    .catch(next)

export const destroy = ({ params }, res, next) =>
  Photorestaurant.findById(params.id)
    .then(notFound(res))
    .then((photorestaurant) => photorestaurant ? photorestaurant.remove() : null)
    .then(success(res, 204))
    .catch(next)
