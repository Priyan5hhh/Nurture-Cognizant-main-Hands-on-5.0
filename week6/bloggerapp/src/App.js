import { useState } from 'react';
import './App.css';
import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';

const TABS = ['book', 'blog', 'course', 'all'];

function App() {
  const [activeTab, setActiveTab] = useState('all');

  // Conditional rendering with a lookup map instead of if/else or switch
  const singleView = {
    book: <BookDetails />,
    blog: <BlogDetails />,
    course: <CourseDetails />,
  }[activeTab];

  return (
    <div className="App">
      <h1>Blogger App</h1>

      <nav className="tabs">
        {TABS.map((tab) => (
          <button
            key={tab}
            className={tab === activeTab ? 'active' : ''}
            onClick={() => setActiveTab(tab)}
          >
            {tab === 'all' ? 'All' : tab.charAt(0).toUpperCase() + tab.slice(1)}
          </button>
        ))}
      </nav>

      {/* Rendering multiple components together via a fragment */}
      {activeTab === 'all' ? (
        <>
          <BookDetails />
          <BlogDetails />
          <CourseDetails />
        </>
      ) : (
        singleView
      )}
    </div>
  );
}

export default App;
