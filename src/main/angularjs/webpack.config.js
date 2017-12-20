module.exports = {
  target: 'web',
  devtool: 'source-map',
  entry: {
    main: './app/javascript/ng-dashbot'
  },
  output: {
    path: __dirname + '/../resources/static/',
    filename: 'dashbot.bundle.js'
  },
  resolve: {
    modules: ['node_modules', 'app/javascript']
  },
  externals: {
    angular: 'angular'
  }
};
