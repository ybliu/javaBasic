define(function(require,exports,module){ 
    console.log('module of main1:');

    module.exports = { 
        say: function(){ 
            console.log('main1--hello');
        }
    };
});