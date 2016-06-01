define(function(require,exports,module){ 
    console.log('module of main:');
    var main1 = require('mymain/main1');
    main1.say();
    var main2 = require('mymain/main2');
    main2.say();

    return { 
        say: function(){ 
            console.log('main--hello');
        }
    };

});