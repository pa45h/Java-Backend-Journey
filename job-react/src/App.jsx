import { BrowserRouter, Route, Routes } from "react-router";
import "./App.css";
import Home from "./Components/Home";
import FindJobs from "./Components/FindJobs";
import AddJobs from "./Components/AddJobs";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/find-jobs" element={<FindJobs />} />
          <Route path="/add-jobs" element={<AddJobs />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
