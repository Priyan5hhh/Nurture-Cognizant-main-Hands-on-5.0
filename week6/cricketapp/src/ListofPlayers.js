import React from 'react';

const players = [
  { name: 'Rohit Sharma', score: 85 },
  { name: 'Virat Kohli', score: 92 },
  { name: 'Shubman Gill', score: 65 },
  { name: 'KL Rahul', score: 58 },
  { name: 'Suryakumar Yadav', score: 74 },
  { name: 'Hardik Pandya', score: 60 },
  { name: 'Ravindra Jadeja', score: 45 },
  { name: 'MS Dhoni', score: 88 },
  { name: 'Jasprit Bumrah', score: 20 },
  { name: 'Mohammed Shami', score: 15 },
  { name: 'Kuldeep Yadav', score: 30 },
];

function ListofPlayers() {
  const belowSeventy = players.filter((player) => player.score < 70);

  return (
    <div>
      <h2>List of Players</h2>
      <ul>
        {players.map((player, index) => (
          <li key={index}>
            {player.name} - {player.score}
          </li>
        ))}
      </ul>

      <h2>Players with Score Below 70</h2>
      <ul>
        {belowSeventy.map((player, index) => (
          <li key={index}>
            {player.name} - {player.score}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ListofPlayers;
