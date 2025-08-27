// src/components/FloatingActionButton.tsx
import React from 'react';

const buttonStyles = {
    floatingButton: {
        position: 'absolute' as const,
        bottom: '80px',
        right: '20px',
        width: '60px',
        height: '60px',
        borderRadius: '50%',
        backgroundColor: '#1a73e8',
        color: 'white',
        fontSize: '36px',
        border: 'none',
        boxShadow: '0 4px 12px rgba(0,0,0,0.25)',
        cursor: 'pointer',
        display: 'flex' as const,
        justifyContent: 'center',
        alignItems: 'center',
        zIndex: 100,
        transition: 'transform 0.2s ease-out',
    }
};

interface FloatingActionButtonProps {
    onClick: () => void;
}

export const FloatingActionButton: React.FC<FloatingActionButtonProps> = ({ onClick }) => {
    return (
        <button
            style={buttonStyles.floatingButton}
            onClick={onClick}
            onMouseOver={(e) => e.currentTarget.style.transform = 'scale(1.05)'}
            onMouseOut={(e) => e.currentTarget.style.transform = 'scale(1)'}
        >
            +
        </button>
    );
};