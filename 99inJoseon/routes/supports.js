var express = require('express');
var router = express.Router();
var mongoose = require('mongoose');

var supportSchema = mongoose.Schema({
	id: String,
	time: { type : Date, default : Date.now }, // year month date hour min sec ex) 20160722163915
	title: String,
	image: String,
	name: String,
	price: { type : Number },
	location: String,
	info: String,
	complete: Boolean
});

var Support = mongoose.model('support', supportSchema);

var j = 0;
var result = [];
var json1 = null;

router.post('/my_posts', function (req, res, next) {
	Support.find({ id: req.body.id }).sort({ time: -1 }, function (err, docs) {
		for (var i=0; i<docs.length; i++) {
                    		json1 = ({
				id: docs[i].id,
                        			time: docs[i].time,
                        			title: docs[i].title,
                        			image: docs[i].image,
                        			name: docs[i].name,
                        			price: docs[i].price,
                        			location: docs[i].location,
                        			info: docs[i].info,
                        			complete: docs[i].complete
                        		});
                       		result[j] = json1;
                        		json1 = null;
                            	j++;
		}
            		res.json(
           			result
           		);

		res.sendStatus(200);
        		console.log(result);
        		result = [];
        		j = 0;
	});
});

router.post('/lastest_4', function (req, res, next) {
	Support.find().sort({ time: -1 }).limit(4, function (err, docs) {
		for (var i=0; i<docs.length; i++) {
                    		json1 = ({
				id: docs[i].id,
                        			time: docs[i].time,
                        			title: docs[i].title,
                        			image: docs[i].image,
                        			name: docs[i].name,
                        			price: docs[i].price,
                        			location: docs[i].location,
                        			info: docs[i].info,
                        			complete: docs[i].complete
                        		});

                        		result[j] = json1;
                            	json1 = null;
                            	j++;
		}
            		res.json(
            			result
            		);

		res.sendStatus(200);
        		console.log(result);
        		result = [];
        		j = 0;
	});
});

router.post('/lastest', function (req, res, next) {
	Support.find().sort({ time: -1 }, function (err, docs) {
		for (var i=0; i<docs.length; i++) {
                    		json1 = ({
				id: docs[i].id,
                        			time: docs[i].time,
                        			title: docs[i].title,
                        			image: docs[i].image,
                        			name: docs[i].name,
                        			price: docs[i].price,
                        			location: docs[i].location,
                        			info: docs[i].info,
                        			complete: docs[i].complete
                        		});
                       		result[j] = json1;
                        		json1 = null;
                            	j++;
		}
            		res.json(
           			result
           		);

            		res.sendStatus(200);
        		console.log(result);
        		result = [];
        		j = 0;
	});
});

router.post('/posting', function (req, res, next) {
	if (!docs) {
		var support = new Support({
                    		id: req.body.id,
                        		time: Date.now,
                        		title: req.body.title,
                        		image: req.body.image,
                        		name: req.body.name,
                        		price: req.body.price,
                        		location: req.body.location,
                        		info: req.body.info,
                        		complete: false
                	});
                	res.json({
                    		message: 'ok'
                	});
		support.save();
		res.sendStatus(200);
                	console.log(req.body);
	}
});

router.post('/find', function (req, res, next) {
	Support.find({ title: { '$regex': '.*' + req.body.title + '.*' } }).sort({ time: -1 }, function (err, docs) {
		for (var i=0; i<docs.length; i++) {
                    		json1 = ({
				id: docs[i].id,
                        			time: docs[i].time,
                        			title: docs[i].title,
                        			image: docs[i].image,
                        			name: docs[i].name,
                        			price: docs[i].price,
                        			location: docs[i].location,
                        			info: docs[i].info,
                        			complete: docs[i].complete
                        		});
                       		result[j] = json1;
                        		json1 = null;
                            	j++;
		}
            		res.json(
           			result
           		);

            		res.sendStatus(200);
        		console.log(result);
        		result = [];
        		j = 0;
	});
});

router.get('/', function (req, res) {
	Support.find().sort({ name: -1 }, function (err, docs) {
		for (var i=0; i<docs.length; i++) {
                    		json1 = ({
				id: docs[i].id,
                        			time: docs[i].time,
                        			title: docs[i].title,
                        			image: docs[i].image,
                        			name: docs[i].name,
                        			price: docs[i].price,
                        			location: docs[i].location,
                        			info: docs[i].info,
                        			complete: docs[i].complete
                        		});
                       		result[j] = json1;
                        		json1 = null;
                            	j++;
		}
            		res.json(
           			result
           		);

            		res.sendStatus(200);
        		console.log(result);
        		result = [];
        		j = 0;
	});
});

module.exports = router;
