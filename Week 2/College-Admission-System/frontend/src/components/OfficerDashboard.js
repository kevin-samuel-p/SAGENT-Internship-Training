import React, { useState, useEffect } from 'react';
import axios from 'axios';

const OfficerDashboard = () => {
    const [apps, setApps] = useState([]);
    const token = localStorage.getItem('token');
    const config = { headers: { Authorization: `Bearer ${token}` } };

    useEffect(() => {
        fetchApps();
    }, []);

    const fetchApps = async () => {
        const res = await axios.get('http://localhost:8080/api/admin/applications', config);
        setApps(res.data);
    };

    const updateStatus = async (id, status) => {
        await axios.put(`http://localhost:8080/api/admin/update-status/${id}?status=${status}`, {}, config);
        fetchApps();
    };

    return (
        <div>
            <h2>Officer Dashboard</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>App ID</th><th>Student</th><th>Grades</th><th>Doc</th><th>Current Status</th><th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {apps.map(app => (
                        <tr key={app.applicationId}>
                            <td>{app.applicationId}</td>
                            <td>{app.student.email}</td>
                            <td>{app.grades}</td>
                            <td><a href={`http://localhost:8080/uploads/${app.documentPath}`} target="_blank" rel="noreferrer">View</a></td>
                            <td>{app.status}</td>
                            <td>
                                <button onClick={() => updateStatus(app.applicationId, 'ACCEPTED')}>Accept</button>
                                <button onClick={() => updateStatus(app.applicationId, 'REJECTED')}>Reject</button>
                                <button onClick={() => updateStatus(app.applicationId, 'UNDER_REVIEW')}>Review</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default OfficerDashboard;