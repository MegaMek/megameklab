import React, { ReactNode } from 'react';
import Head from 'next/head';
// import Link from 'next/link'; // Example if you want a global nav bar

interface LayoutProps {
  children: ReactNode;
  title?: string;
  sidebar?: ReactNode; // Optional sidebar content
}

const Layout: React.FC<LayoutProps> = ({ children, title = 'BattleTech Editor', sidebar }) => {
  return (
    <>
      <Head>
        <title>{title}</title>
        <meta charSet="utf-8" />
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
        {/* Add other meta tags, favicons etc. here */}
      </Head>

      {/* Basic global header example - uncomment and style if needed
      <header className="bg-gray-800 text-white p-4">
        <nav className="container mx-auto flex justify-between">
          <Link href="/" legacyBehavior><a className="hover:text-gray-300">Home</a></Link>
          <div>
            <Link href="/units" legacyBehavior><a className="mr-4 hover:text-gray-300">Units</a></Link>
            <Link href="/equipment" legacyBehavior><a className="hover:text-gray-300">Equipment</a></Link>
          </div>
        </nav>
      </header>
      */}

      <div className="flex min-h-screen bg-gray-50">
        {sidebar && (
          <aside className="w-full md:w-64 bg-gray-100 p-4 shadow-md print:hidden fixed h-full overflow-y-auto md:relative"> {/* Adjust for mobile fixed sidebar */}
            {sidebar}
          </aside>
        )}
        <main className={`flex-grow p-4 sm:p-6 ${sidebar ? 'md:pl-64' : ''} w-full`}> {/* Adjust padding if fixed sidebar on mobile */}
          {/* Content width control can be managed by child page or here if a global max is desired */}
          {/* Example: <div className="max-w-7xl mx-auto"> {children} </div> */}
          {children}
        </main>
      </div>

      <footer className="text-center p-4 bg-gray-200 text-xs text-gray-600 print:hidden">
        <span>BattleTech Data Editor & Viewer | Concept by Jules</span>
      </footer>
    </>
  );
};

export default Layout;
