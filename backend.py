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
data = ref.get()
if data is None:
    print(f"User \"{user}\" does not exist in the database.")
else:
    sorted_data = sorted(data.items(), key=lambda x: x[1]['timeStamp'], reverse=True)
    print(f"Swipe Data for {user}:\n")
    for key, value in sorted_data:
        print(f"Timestamp: {value['timeStamp']}")
        print(f"  - Direction: {value['swipeDirection']}")
        print(f"  - Duration: {value['swipeDuration']} ms")
        print("-" * 40)