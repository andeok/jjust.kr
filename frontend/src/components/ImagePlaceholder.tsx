// src/components/ImagePlaceholder.tsx
import React from 'react';

const placeholderStyles = {
    container: {
        width: '100%',
        height: '100%',
        backgroundColor: '#f0f0f0',
        display: 'flex' as const,
        justifyContent: 'center',
        alignItems: 'center',
        color: '#aaa',
        borderRadius: '8px'
    },
};

const CameraIcon = () => (
    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
        <path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"></path>
        <circle cx="12" cy="13" r="4"></circle>
    </svg>
);

export const ImagePlaceholder: React.FC = () => (
    <div style={placeholderStyles.container}>
        <CameraIcon />
    </div>
);