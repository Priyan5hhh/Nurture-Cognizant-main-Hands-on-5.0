import { render, screen, fireEvent } from '@testing-library/react';
import App from './App';

test('renders guest page by default', () => {
  render(<App />);
  expect(screen.getByText(/guest - browse flights/i)).toBeInTheDocument();
  expect(screen.getByText(/login/i)).toBeInTheDocument();
});

test('shows user page after login and back to guest page after logout', () => {
  render(<App />);

  fireEvent.click(screen.getByText(/login/i));
  expect(screen.getByText(/welcome, user - book your flight/i)).toBeInTheDocument();

  fireEvent.click(screen.getByText(/logout/i));
  expect(screen.getByText(/guest - browse flights/i)).toBeInTheDocument();
});
