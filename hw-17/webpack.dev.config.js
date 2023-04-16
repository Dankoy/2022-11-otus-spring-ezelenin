const HtmlWebpackPlugin = require('html-webpack-plugin')
const path = require('path');

console.log(process.env.BACKEND_HOST); // переменные окружения в докер контейнере
console.log(process.env.BACKEND_PORT); // переменные окружения в докер контейнере

module.exports = {
    entry: './src/ui/index.js',
    devtool: 'inline-source-map',
    mode: 'development',
    output: {
        path: path.resolve(__dirname),
        filename: 'bundle.js',
        libraryTarget: 'umd'
    },

    devServer: {
        static: path.resolve(__dirname) + '/src/ui',
        compress: true,
        port: 9000,
        host: '0.0.0.0',
        open: true,

/*
        setupMiddlewares: (middlewares, devServer) => {
            middlewares.unshift({
                name: 'inital-data-mw',
                path: '/api/persons',
                middleware: (req, res) => res.send([
                    {id: '1', name: 'Привяу'}
                ])
              });
            return middlewares;
        },
*/       
        proxy: {
            "*": {
                target: {
                    host: process.env.BACKEND_HOST,
                    protocol: 'http:',
                    port: process.env.BACKEND_PORT
                },
                // ignorePath: true,
                changeOrigin: true,
                secure: false
            }
        }
        
        
    },

    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules|bower_components|build)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", '@babel/preset-react']
                    }
                }
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: 'src/ui/index.html'
        })
    ]
}
