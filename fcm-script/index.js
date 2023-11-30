const admin = require('firebase-admin');

admin.initializeApp({
  credential: admin.credential.cert('./serviceAccountKey.json'),
});

let message = {
  data: {
    id: "123",
    type: "LIVE_NOTIFICATION",
    title: 'Live Notification',
    description: 'Stay informed! Your current task is successfully underway',
    step: 'first_step', // second_step, third_step
  },
  topic: 'live_notification'
};

admin.messaging().send(message)
  .then((response) => {
    console.log('Successfully sent message:', response);
  })
  .catch((error) => {
    console.log('Error sending message:', error);
  });