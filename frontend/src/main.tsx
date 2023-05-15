import React from 'react';
import ReactDOM from 'react-dom/client';
import { GlobalStyles } from './styles/GlobalStyle';
import { QueryClientProvider, QueryClient } from '@tanstack/react-query';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import MainPage from './pages/Main/Main.tsx';
import CreateRoomPage from './pages/CreateRoom/CreateRoom.tsx';

import { Signup } from './pages/Signup/index.tsx';

if (process.env.NODE_ENV === 'development') {
  const { worker } = require('./__mocks__/browser');
  worker.start();
}

let router = createBrowserRouter([
  {
    path: '/',
    Component: () => <MainPage />,
  },
  {
    path: '/signup',
    Component: () => <Signup />,
  },
  {
    path: '/createRoom',
    Component: () => <CreateRoomPage />,
  },
]);

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <GlobalStyles />
      <RouterProvider router={router} fallbackElement={<p>Loading...</p>} />
    </QueryClientProvider>
  </React.StrictMode>
);
