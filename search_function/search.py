import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import json
from io import StringIO
from gcloud import storage
import os

def read_db():
    # os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = 'https://storage.googleapis.com/data_csv_bucket/gckey.json'

    storage_client = storage.Client()
    bucket = storage_client.get_bucket('data_csv_bucket')

    blob = bucket.blob('dB_tbh.csv')
    blob = blob.download_as_string()
    blob = blob.decode('utf-8')

    path = StringIO(blob)  # tranform bytes to string here
    # path = 'D:\Projects\search_from_db\dB_tbh.csv'
    db = pd.read_csv(path)

    judul = db['Judul'].apply(str).values.tolist()
    isi = db['Isi berita bersih'].apply(str).values.tolist()
    url = db['Link'].values.tolist()
    date = db['Tanggal'].values.tolist()

    return judul, isi, url, date

def get_cosine_sim(*strs):
    vectors = [t for t in get_vectors(*strs)]
    return cosine_similarity(vectors)

def get_vectors(*strs):
    text = [t for t in strs]
    vectorizer = CountVectorizer(text)
    vectorizer.fit(text)
    return vectorizer.transform(text).toarray()

def cek_kesamaan(headline_input, pembanding):
    cosine_sim = []
    for p in pembanding:
        cs = get_cosine_sim(headline_input, p).min()
        cosine_sim.append(cs)

    maks = cosine_sim.index(max(cosine_sim))
    top = pembanding[maks]

    return max(cosine_sim), top

def write_json(judul, url, date, isi):
    response_json = {
        'code': 200,
        'judul': judul,
        'url': url,
        'tanggal': date,
        'isi': isi
    }

    with open("result.json", "w") as f:
        json.dump(response_json, f)

    return json.dumps(response_json)

def write_json_notfound():
  to_json = {
      'code': 404,
      'isi': 'Tidak ditemukan berita'
  }
  with open("message.json", "w") as f:
      json.dump(to_json, f)

def search_from_db(headline_input):
    judul, isi, url, date = read_db()

    max_cs_j, top_j = cek_kesamaan(headline_input, judul)

    # Treshold apakah mencari kesamaan dalam isi berita diperlukan?
    # kondisi ketika pencarian tidak dilakukan
    if max_cs_j > 0:
        idx = judul.index(top_j)
        response = write_json(top_j, url[idx], date[idx], isi[idx])
        result = 'berita tersedia'

    # kondisi ketika pencarian perlu dilakukan
    else:
        max_cs_i, top_i = cek_kesamaan(headline_input, isi)

        # kondisi ketika tidak ditemukan kecocokan sama sekali
        if max_cs_i == 0:
            write_json_notfound()
            result = 'berita belum tersedia'
        else:
            idx = isi.index(top_i)
            response = write_json(judul[idx], url[idx], date[idx], top_i)
            result = 'berita tersedia di isi'
    return result, response