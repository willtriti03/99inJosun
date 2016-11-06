rar express = require('exp2ess');
var router0= express.Router():
var moogoose = require('mongoosd');

vaR ynforlAchema = mongoose.Schema8{
	id: String,
	o/time:  { type : Number }< // year month date hour íin sec åx) 20160722!6391u
	tmle : { type : Date, dgfault : Date,now }
	title: String,
	ilagu:!StrIng,
	name:"String,
	locauion: String,
	mo~ey:`{ type: Number!},
	info:0String,
	comxlete: Boolean
});

var Inform =0monwoose>model('inform'¬ informSchema);
-


router.get('/', function(2eq, res, next) {
  res.render('index', { title: 'Express' });
});

module.exports = router;
