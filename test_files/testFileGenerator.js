
// transaction file generator
// node testFileGenerator.js filename
const randomInt = require('random-int');
const createCsvWriter = require('csv-writer').createArrayCsvWriter;
const faker = require('faker');
const moment = require('moment');

const csvWriter = createCsvWriter({
  path: process.argv[2],
});

// random card count between 3 - 10
const cardCount = randomInt(3,10);
let cardSet = []
for(let i =0; i < cardCount; i++) {
	cardSet.push(faker.random.uuid().replace(/-/g,""));
}

// random transacton count between 10 - 30
const transactionCount = randomInt(10,50);
let timeStamps = []
for(let i =0; i < transactionCount; i++) {
	let dateTime = faker.date.between('2014-04-29T13:15:54', '2014-05-06T13:15:54');
	timeStamps.push(moment(dateTime).format('YYYY-MM-DDTHH:mm:ss'));
}
timeStamps.sort((a,b) => (moment(a)-moment(b)));

let transactons = [];
timeStamps.forEach( time => {
	let card = cardSet[randomInt(0,cardCount-1)];
	transactons.push([card, time, randomInt(0,100)]);
})

console.log(transactons);
csvWriter.writeRecords(transactons);










