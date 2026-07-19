import React from 'react';

const indianTeam = [
  'Rohit Sharma',
  'Virat Kohli',
  'Shubman Gill',
  'KL Rahul',
  'Suryakumar Yadav',
  'Hardik Pandya',
  'Ravindra Jadeja',
  'MS Dhoni',
  'Jasprit Bumrah',
  'Mohammed Shami',
  'Kuldeep Yadav',
];

const [
  oddPlayer1, evenPlayer1,
  oddPlayer2, evenPlayer2,
  oddPlayer3, evenPlayer3,
  oddPlayer4, evenPlayer4,
  oddPlayer5, evenPlayer5,
  oddPlayer6,
] = indianTeam;

const oddTeamPlayers = [oddPlayer1, oddPlayer2, oddPlayer3, oddPlayer4, oddPlayer5, oddPlayer6];
const evenTeamPlayers = [evenPlayer1, evenPlayer2, evenPlayer3, evenPlayer4, evenPlayer5];

const T20players = ['Rohit Sharma', 'Virat Kohli', 'Suryakumar Yadav', 'Hardik Pandya'];
const RanjiTrophyPlayers = ['Prithvi Shaw', 'Sarfaraz Khan', 'Mayank Agarwal', 'Yashasvi Jaiswal'];

const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

function IndianPlayers() {
  return (
    <div>
      <h2>Odd Team Players</h2>
      <ul>
        {oddTeamPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>

      <h2>Even Team Players</h2>
      <ul>
        {evenTeamPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>

      <h2>T20 & Ranji Trophy Players (Merged)</h2>
      <ul>
        {mergedPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>
    </div>
  );
}

export default IndianPlayers;
