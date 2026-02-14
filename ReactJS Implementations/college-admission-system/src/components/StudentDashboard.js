import React, { useState, useEffect } from 'react';
import axios from 'axios';

const StudentDashboard = () => {
    const [apps, setApps] = useState([]);
    const [form, setForm] = useState({ dob: '', address: '', grades: '', course: '' });
    const [file, setFile] = useState(null);

    const token = localStorage.getItem('token');
    const config = { headers: { Authorization: `Bearer ${token}` } };

    useEffect(() => {
        fetchApps();
    }, []);

    const fetchApps = async () => {
        const res = await axios.get('http://localhost:8080/api/student/my-applications', config);
        setApps(res.data);
    };

    const handleSubmit = async () => {
        const formData = new FormData();
        formData.append('name', 'Student'); // Dummy
        formData.append('dob', form.dob);
        formData.append('address', form.address);
        formData.append('grades', form.grades);
        formData.append('course', form.course);
        formData.append('file', file);

        await axios.post('http://localhost:8080/api/student/apply', formData, {
            headers: { ...config.headers, 'Content-Type': 'multipart/form-data' }
        });
        alert('Application Submitted');
        fetchApps();
    };

    const handlePay = async (id) => {
        await axios.post(`http://localhost:8080/api/student/pay/${id}`, {}, config);
        fetchApps();
    };

    const handleCancel = async (id) => {
        await axios.post(`http://localhost:8080/api/student/cancel/${id}`, {}, config);
        fetchApps();
    };

    return (
        <div>
            <h2>New Application</h2>
            <input placeholder="DOB" onChange={e => setForm({...form, dob: e.target.value})} />
            <input placeholder="Address" onChange={e => setForm({...form, address: e.target.value})} />
            <input placeholder="Grades" onChange={e => setForm({...form, grades: e.target.value})} />
            <select onChange={e => setForm({...form, course: e.target.value})}>
                <option value="">Select Course</option>
                <option value="CS">Computer Science</option>
                <option value="ME">Mechanical</option>
            </select>
            <input type="file" onChange={e => setFile(e.target.files[0])} />
            <button onClick={handleSubmit}>Submit Application</button>

            <h3>My Applications</h3>
            <ul>
                {apps.map(app => (
                    <li key={app.applicationId}>
                        ID: {app.applicationId} | Course: {app.course} | Status: <b>{app.status}</b>
                        {!app.feePaid && app.status !== 'CANCELLED' && 
                            <button onClick={() => handlePay(app.applicationId)}>Pay Fee</button>}
                        {app.status !== 'CANCELLED' && 
                            <button onClick={() => handleCancel(app.applicationId)}>Cancel</button>}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default StudentDashboard;