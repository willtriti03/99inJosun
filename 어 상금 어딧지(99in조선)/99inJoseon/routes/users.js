var express = require('express');
var router = express.Router();
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/sunrinton2016');

var userSchema = mongoose.Schema({
	id: String,
	password: String,
	rank: String
});                 

var User = mongoose.model('user', userSchema);

var j = 0;
var result = {};
var json1 = null;
/* GET users listing. */
router.get('/', function (req, res, next) {
	User.find(function (err, docs) {
		if (docs.length == 0) {
            		res.send(403);
        		} else {
            		for (var i = 0; i < docs.length; i++) {
                    			json1 = ({
					id: docs[i].id,
                        			password: docs[i].password,
                        			rank: docs[i].rank
                        		});

                            		result[j] = json1;
                            		json1 = null;
                            		j++;
                		}
            	}
            	res.json(
            		result
            	);
        		console.log(result);
        		result = {};
        		j = 0;
	});
});

router.post('/me', function (req, res, next) {
	User.find({ id: req.body.id }, function (err, docs) {
		if (docs.length == 0) {
            		res.sendStatus(304); // id doesn't equal
        		} else {
                		/*res.json({
                    			id: req.body.id,
                    			password: req.body.password,
                    			rank: req.body.rank
                		});*/
			res.send(req.body.rank);
                		//res.sendStatus(200);
                		console.log(req.body);
        		}
	});
});

router.post('/login', function (req, res, next) {
	User.find({ id: req.body.id }, function (err, docs) {
		if (docs.length == 0) {
            			res.sendStatus(304); // id doesn't equal
        		} else {
            		if (docs[0].password == req.body.password) {
                			res.json({
                    				rank: docs[0].rank
                			});
                			//res.sendStatus(200);
                			console.log(req.body);
            		} else {
                			res.sendStatus(403); // password doesn't equal
            		}
        		}
	});
});

router.post('/logout', function (req, res) {
    	res.json({
        		message: '로그아웃 되었습니다.'
    	});
});

router.post('/signup', function (req, res, next) {
	User.findOne({ id: req.body.id }, function (err, doc) {
        		if (!doc) {
                		var user = new User ({
                    			id: req.body.id,
                    			password: req.body.password,
                    			rank: req.body.rank
                		});
                		res.json({
                    			message: 'ok'
                		});
                		user.save();
                		console.log(req.body);

                		//res.sendStatus(200);
            	} else {
                		res.sendStatus(304); // 이미 중복된 id가 있을 시
        		}
    	});
});

module.exports = router;
