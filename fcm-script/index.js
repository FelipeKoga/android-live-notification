const admin = require('firebase-admin');

admin.initializeApp({
  credential: admin.credential.cert('./serviceAccountKey.json'),
});

let message = {
  data: {
    progress: "24",
    title: 'my custom title',
    description: 'my custom description'
  },
  topic: 'real_time_notification'
};

admin.messaging().send(message)
  .then((response) => {
    console.log('Successfully sent message:', response);
  })
  .catch((error) => {
    console.log('Error sending message:', error);
  });