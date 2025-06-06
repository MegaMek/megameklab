import React, { ReactNode } from 'react';
import Head from 'next/head';
import Link from 'next/link';

type LayoutProps = {
  children: ReactNode;
  title?: string;
};

const Layout = ({ children, title = 'BattleTech Editor' }: LayoutProps) => {
  return (
    <div className="min-h-screen bg-gray-100">
      <Head>
        <title>{title}</title>
        <meta charSet="utf-8" />
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
      </Head>
      <header className="bg-gray-800 text-white p-4">
        <nav className="container mx-auto flex justify-between">
          <Link href="/" className="text-xl font-bold">
            BT Editor
          </Link>
          <div>
            <Link href="/units" className="mr-4 hover:text-gray-300">
              Units
            </Link>
            <Link href="/equipment" className="hover:text-gray-300">
              Equipment
            </Link>
          </div>
        </nav>
      </header>
      <main className="container mx-auto p-4">
        {children}
      </main>
      <footer className="bg-gray-700 text-white p-4 text-center">
        <p>&copy; {new Date().getFullYear()} BattleTech Editor Project</p>
      </footer>
    </div>
  );
};

export default Layout;
