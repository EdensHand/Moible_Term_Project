import firebase_admin
from firebase_admin import credentials, db

# Path to the service account key file
cred = credentials.Certificate("./touchalytics-27705-firebase-adminsdk-fbsvc-faa52519b1.json")
firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://touchalytics-27705-default-rtdb.firebaseio.com/'
})

# Writing data to the database
user = input("Please enter the username of the individual whose data you'd like to retrieve: ")
print()
ref = db.reference(user)
message = ref.get()
if message == None:
    print(f"User \"{user}\" does not exist in the database.")
else:
    #print(f"Read from Firebase: {message}")
    for key, value in message.items():
        for item in str(value).split(','):
            print(item.replace("'",'').replace("{",'').replace("}",''))

print()

#test commit in pycharm