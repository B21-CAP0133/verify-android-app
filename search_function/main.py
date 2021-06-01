from search import *
from flask import Flask, request
app = Flask(__name__)

@app.route("/")
def health_check():
    return "It works!"

@app.route("/search", methods = ["POST"])
def get_data():
    request_json = request.json
    print("message: {}".format(request_json))
    headline_input = request_json.get('message')
    print(headline_input)
    hasil, response = search_from_db(headline_input)
    return response

if __name__ == '__main__':
    app.run(host='0.0.0.0', port = 5000)