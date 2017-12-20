var angular = require('angular'); 
module.exports = angular.module('dashbot.config', [])
  .constant('dashbot.config.environment', {host:'',apiRoot:'/api/',apiJsonExtension:'',apiHtmlExtension:'',templateRoot:'/templates/',templateExtension:'.html',layoutUrl:'/layout.json'})
  .constant('dashbot.config.log', {enabled:true})
  .constant('dashbot.config.ui', {});