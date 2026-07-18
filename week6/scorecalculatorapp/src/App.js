import './App.css';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div className="App">
      <CalculateScore name="Priyansh" school="Cognizant School" total={450} goal={5} />
    </div>
  );
}

export default App;
