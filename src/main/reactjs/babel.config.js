module.exports = {
    presets: [
        ['@babel/preset-env'],
        ['@babel/preset-flow'],
        ['@babel/preset-react']
    ],

    "env": {
        "test": {
            "presets": [
                ["@babel/preset-env", {
                    "targets": {
                        "node": "current"
                    }
                }]
            ]
        }
    }
};
