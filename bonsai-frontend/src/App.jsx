import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Profile from "./components/Profile";
import Workout from "./components/Workout";
import UserWeight from "./components/UserWeight";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/workout" element={<Workout />} />
        <Route path="/weight" element={<UserWeight />} />
      </Routes>
    </Router>
  );
}

export default App;
