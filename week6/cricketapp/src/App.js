import './App.css';
import ListofPlayers from './ListofPlayers';
import IndianPlayers from './IndianPlayers';

const flag = true;

function App() {
  let content;

  if (flag) {
    content = <ListofPlayers />;
  } else {
    content = <IndianPlayers />;
  }

  return <div className="App">{content}</div>;
}

export default App;
