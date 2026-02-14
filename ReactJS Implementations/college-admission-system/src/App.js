import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import StudentDashboard from './components/StudentDashboard';
import OfficerDashboard from './components/OfficerDashboard';

function App() {
  return (
    <Router>
      <nav style={{ padding: 10, background: '#eee' }}>
        <Link to="/login">Login</Link> | <Link to="/register">Register</Link>
      </nav>
      <div style={{ padding: 20 }}>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/student" element={<StudentDashboard />} />
          <Route path="/officer" element={<OfficerDashboard />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;