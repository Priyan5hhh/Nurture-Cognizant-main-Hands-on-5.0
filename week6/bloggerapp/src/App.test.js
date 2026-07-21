import { render, screen, fireEvent } from '@testing-library/react';
import App from './App';

test('renders all three sections by default', () => {
  render(<App />);
  expect(screen.getByText(/book details/i)).toBeInTheDocument();
  expect(screen.getByText(/blog details/i)).toBeInTheDocument();
  expect(screen.getByText(/course details/i)).toBeInTheDocument();
});

test('shows only book details when the Book tab is clicked', () => {
  render(<App />);
  fireEvent.click(screen.getByText('Book'));
  expect(screen.getByText(/book details/i)).toBeInTheDocument();
  expect(screen.queryByText(/blog details/i)).not.toBeInTheDocument();
});
