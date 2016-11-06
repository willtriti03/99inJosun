var express = require('express');
var router = express.Router();
var mongoose = require('mongoose');

var noticeSchema = mongoose.Schema({
	id: String,
	//time:  { type : Number }, // year month date hour min sec ex) 20160722163915
	time : { type : Date, default : Date.now },
	title: String,
	//image: [Buffer],
	image: String,
	name: String,
	location: String,
	money: { type: Number },
	info: String,
	complete: Boolean
});

var Notice = mongoose.model('notice', noticeSchema);

var j = 0;
var result = [];
var json1 = null;

router.post('/my_posts', function (req, res, next) {
	Notice.find({ id: req.body.id }).sort({ time: -1 }, function (err, docs) {
		for (var i=0; i<docs.length; i++) {
                    		json1 = ({
				id: docs[i].id,
                        			time: docs[i].time,
                        			title: docs[i].title,
                        			image: docs[i].image,
                        			name: docs[i].name,
                        			location: docs[i].location,
                        			money: docs[i].money,
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

            		//res.sendStatus(200);
        		console.log(result);
        		result = [];
        		j = 0;
	});
});

router.post('/lastest_4', function (req, res, next) {
	Notice.find().sort({  name: -1 }).limit(4, function (err, docs) {
		for (var i=0; i<docs.length; i++) {
                    		json1 = ({
				id: docs[i].id,
                        			time: docs[i].time,
                        			title: docs[i].title,
                        			image: docs[i].image,
                        			name: docs[i].name,
                        			location: docs[i].location,
                        			money: docs[i].money,
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

		//res.sendStatus(200);
        		console.log(result);
        		result = [];
        		j = 0;
	});
});

router.post('/lastest', function (req, res, next) {
	Notice.find().sort({ name: -1 }).exec(function (err, logs) {
		
            	res.json(
           			logs
           		);

            		//res.sendStatus(200);
        		console.log(logs);
	});
});

router.post('/posting', function (req, res, next) {
		var notice = new Notice({
                    		id: req.body.id,
                        		time: Date.now,
                        		title: req.body.title,
                        		image: req.body.image,
                        		name: req.body.name,
                        		location: req.body.location,
                        		money: req.body.money,
                        		info: req.body.info,
                        		complete: false
                	});
		notice.save();
                	res.json({
                    		message: 'ok'
                	});
		//res.sendStatus(200);
                	console.log(req.body);
});

router.post('/find', function (req, res, next) {
	Notice.find({ title: { '$regex': '.*' + req.body.title + '.*' } }).sort({ time: -1 }).exec(function (err, docs) {
		for (var i=0; i<docs.length; i++) {
                    		json1 = ({
				id: docs[i].id,
                        			time: docs[i].time,
                        			title: docs[i].title,
                        			image: docs[i].image,
                        			name: docs[i].name,
                        			location: docs[i].location,
                        			money: docs[i].money,
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

            		//res.sendStatus(200);
        		console.log(result);
        		result = [];
        		j = 0;
	});
});

router.get('/', function (req, res) {
	Notice.find().sort({ name: -1 }).exec(function (err, logs) {
		
            		res.json(
           			logs
           		);

            		//res.sendStatus(200);
        		console.log(logs);
	});
});
module.exports = router;
