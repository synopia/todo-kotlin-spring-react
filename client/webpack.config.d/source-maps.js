config.module.rules.push(
    {
        test: /\.js$/,
        exclude: [/.*bootstrap.*/, /.*min.js$/],
        use: ['source-map-loader'],
        enforce: 'pre'
    }
)

config.devtool = "inline-source-map"

