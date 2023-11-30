const admin = require('firebase-admin');

admin.initializeApp({
  credential: admin.credential.cert('./serviceAccountKey.json'),
});

let message = {
  data: {
    type: "LIVE_NOTIFICATION",
    title: 'my custom title',
    description: 'my custom description',
    step: 'third_step', // second_step, third_step
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